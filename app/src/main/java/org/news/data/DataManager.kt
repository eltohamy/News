package org.news.data

import org.news.data.remote.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(val remoteApi: RemoteApi)
