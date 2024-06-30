package com.example.pgconnect.utils

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.util.UUID
import javax.security.auth.callback.Callback

fun uploadimage(uri: Uri,foldername:String,callback:(String?)->Unit)
{
    var imageurl:String?=null
FirebaseStorage.getInstance().getReference(foldername).child(UUID.randomUUID().toString()).putFile(uri)
    .addOnSuccessListener {
        it.storage.downloadUrl.addOnSuccessListener {
            imageurl=it.toString()
            callback(imageurl)
        }
    }
}


