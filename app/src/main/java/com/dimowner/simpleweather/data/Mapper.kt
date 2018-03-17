/*
 *  Copyright 2018 Dmitriy Ponomarenko
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership. The ASF licenses this
 *  file to you under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */

package com.dimowner.simpleweather.data

import com.dimowner.simpleweather.data.local.room.WeatherEntity
import com.dimowner.simpleweather.data.remote.model.WeatherListResponse
import com.dimowner.simpleweather.data.remote.model.WeatherListResponse2
import com.dimowner.simpleweather.data.remote.model.WeatherResponse

class Mapper {
	companion object {
		fun convertWeatherResponseToEntity(type: Int, response: WeatherResponse) : WeatherEntity {
			return WeatherEntity(
					type,
					response.dt,
					response.main.temp,
					response.wind.speed,
					response.main.humidity,
					response.main.pressure,
					response.coord.lon,
					response.coord.lat,
					response.weather[0].icon
			)
		}

		fun convertWeatherResponseToEntityList(type: Int, response: WeatherResponse) : List<WeatherEntity> {
			val list = ArrayList<WeatherEntity>()
			list.add(WeatherEntity(
					type,
					response.dt,
					response.main.temp,
					response.wind.speed,
					response.main.humidity,
					response.main.pressure,
					response.coord.lon,
					response.coord.lat,
					response.weather[0].icon
			))
			return list
		}

		fun convertWeatherListResponseToEntityList(type: Int, response: WeatherListResponse) : List<WeatherEntity> {
			val list = ArrayList<WeatherEntity>()
			for (item in response.list) {
				list.add(WeatherEntity(
						type,
						item.dt,
						item.temp.day,
						item.speed,
						item.humidity,
						item.pressure,
						response.city.coord.lon,
						response.city.coord.lat,
						item.weather.icon
				))
			}
			return list
		}
	}
}