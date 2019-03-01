package com.silali.blueprint.auth

import android.content.Context
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.silali.blueprint.R


class AuthController(context : Context) {

    val context = context

    fun currentUser()  {
    }

    fun attemptSignIn() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()


    }

    private fun signIn(googleSignInClient : GoogleSignInClient) {
        val signInIntent = googleSignInClient.signInIntent
    }
}