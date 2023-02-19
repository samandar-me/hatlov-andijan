package com.sdk.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class VariablesResponse(
    val success: Boolean,
    val data: Data
)
data class Data(
    val status: List<VariableStatus>,
    @SerializedName("jins")
    val gender: List<VariableStatus>
)
@Entity
data class VariableStatus(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int = 0,
    val id: Int,
    val name: String
)