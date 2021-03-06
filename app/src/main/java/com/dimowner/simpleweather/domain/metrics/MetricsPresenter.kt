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

package com.dimowner.simpleweather.domain.metrics

import android.content.Context
import com.dimowner.simpleweather.Constants
import com.dimowner.simpleweather.R
import com.dimowner.simpleweather.data.Prefs

open class MetricsPresenter(open val prefs: Prefs, open val context: Context) : MetricsContract.UserActionsListener {

	private var view: MetricsContract.View? = null

	override fun bindView(view: MetricsContract.View) {
		this.view = view
		if (prefs.getTempFormat() == Constants.TEMP_FORMAT_CELSIUS) {
			view.showTemperatureFormat(context.resources.getString(R.string.temp_celsius))
		} else {
			view.showTemperatureFormat(context.resources.getString(R.string.temp_fahrenheit))
		}
		if (prefs.getWindFormat() == Constants.WIND_FORMAT_METER_PER_HOUR) {
			view.showWindFormat(context.resources.getString(R.string.wind_meter_sec))
		} else {
			view.showWindFormat(context.resources.getString(R.string.wind_miles_hour))
		}
		if (prefs.getPressureFormat() == Constants.PRESSURE_FORMAT_MM_HG) {
			view.showPressureFormat(context.resources.getString(R.string.pressure_mm_hg))
		} else {
			view.showPressureFormat(context.resources.getString(R.string.pressure_pha))
		}
		if (prefs.getTimeFormat() == Constants.TIME_FORMAT_24H) {
			view.showTimeFormat(context.resources.getString(R.string.time_format_24h))
		} else {
			view.showTimeFormat(context.resources.getString(R.string.time_format_12h))
		}
	}

	override fun unbindView() {
		this.view = null
	}

	override fun switchTemperature() {
		if (prefs.switchTempFormat() == Constants.TEMP_FORMAT_CELSIUS) {
			view?.showTemperatureFormat(context.resources.getString(R.string.temp_celsius))
		} else {
			view?.showTemperatureFormat(context.resources.getString(R.string.temp_fahrenheit))
		}
	}

	override fun switchWind() {
		if (prefs.switchWindFormat() == Constants.WIND_FORMAT_METER_PER_HOUR) {
			view?.showWindFormat(context.resources.getString(R.string.wind_meter_sec))
		} else {
			view?.showWindFormat(context.resources.getString(R.string.wind_miles_hour))
		}
	}

	override fun switchPressure() {
		if (prefs.switchPressureFormat() == Constants.PRESSURE_FORMAT_MM_HG) {
			view?.showPressureFormat(context.resources.getString(R.string.pressure_mm_hg))
		} else {
			view?.showPressureFormat(context.resources.getString(R.string.pressure_pha))
		}
	}

	override fun switchTimeFormat() {
		if (prefs.switchTimeFormatt() == Constants.TIME_FORMAT_24H) {
			view?.showTimeFormat(context.resources.getString(R.string.time_format_24h))
		} else {
			view?.showTimeFormat(context.resources.getString(R.string.time_format_12h))
		}
	}
}
