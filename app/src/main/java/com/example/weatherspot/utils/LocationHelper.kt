package com.example.weatherspot.utils

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.example.domain.UiState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationHelper(private val context: Context) {
    private val _locationState = MutableStateFlow<UiState<Location?>>(UiState.Loading())
    val locationState = _locationState.asStateFlow()

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { location ->
                _locationState.update { UiState.Success(location) }
            }
        }
    }

    private val locationRequest by lazy {
        LocationRequest.Builder(60000) // Update setiap 1 menit
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
    }

    private val locationSettings by lazy {
        LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)
            .build()
    }

    fun startLocationUpdates() {
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        } catch (e: SecurityException) {
            _locationState.update { UiState.Error(e.message.toString()) }
            Log.e("LocationHelper", "startLocationUpdates() SecurityException: $e")
        }
    }

    // TODO: Fungsi ini belum bisa mendapatkan lokasi terakhir karena lokasi terakhir selalu null
    fun getLastLocation() {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) { // Jika lokasi terakhir tersedia
                        _locationState.update { UiState.Success(location) }
                        Log.d(
                            "LocationHelper",
                            "getLastLocation(): Lat = ${location.latitude}, Long = ${location.longitude}"
                        )
                    } else { // Jika lokasi terakhir tidak tersedia
                        _locationState.update { UiState.Success(null) }
                        Log.d("LocationHelper", "getLastLocation(): Lokasi Terakhir Tidak Tersedia")
                    }
                }.addOnFailureListener {
                    _locationState.update { UiState.Success(null) }
                    Log.d("LocationHelper", "getLastLocation(): Gagal Mendapatkan Lokasi Terakhir")
                }
        } catch (e: SecurityException) {
            _locationState.update { UiState.Error(e.message.toString()) }
            Log.e("LocationHelper", "getLastLocation() SecurityException: $e")
        }
    }

    fun checkAndHandleGpsStatus(
        gpsLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
        onGpsOn: () -> Unit = {}
    ) {
        val settingsClient = LocationServices.getSettingsClient(context)

        settingsClient.checkLocationSettings(locationSettings) // Cek status GPS
            .addOnSuccessListener { // Jika GPS sudah on
                Log.d("LocationHelper", "checkAndHandleGpsActivation(): GPS On")
                onGpsOn()
            }
            .addOnFailureListener { exception -> // Jika GPS off
                Log.d("LocationHelper", "checkAndHandleGpsActivation(): Gps Off")
                if (exception is ResolvableApiException) {
                    try {
                        val intentSenderRequest =
                            IntentSenderRequest.Builder(exception.resolution).build()
                        gpsLauncher.launch(intentSenderRequest) // Tampilkan popup aktivasi GPS
                    } catch (sendEx: IntentSender.SendIntentException) {
                        sendEx.printStackTrace()
                    }
                }
            }
    }

    fun isLocationPermissionGranted(): Boolean =
        hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) &&
                hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

    fun requestLocationPermission(locationLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>) =
        locationLauncher.launch( // Tampilkan popup izin lokasi
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    fun stopLocationUpdates() = fusedLocationClient.removeLocationUpdates(locationCallback)
}