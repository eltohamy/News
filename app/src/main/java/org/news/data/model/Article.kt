package org.news.data.model

import android.os.Parcel
import android.os.Parcelable

class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readParcelable<Parcelable>(Source::class.java.getClassLoader()) as Source,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(source, flags)
        writeString(author)
        writeString(title)
        writeString(description)
        writeString(url)
        writeString(urlToImage)
        writeString(publishedAt)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article = Article(source)
            override fun newArray(size: Int): Array<Article?> = arrayOfNulls(size)
        }
    }
}