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

package com.dimowner.simpleweather.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dimowner.simpleweather.R
import com.dimowner.simpleweather.SWApplication
import com.dimowner.simpleweather.domain.main.WeatherContract
import kotlinx.android.synthetic.main.fragment_weather_details.*
import timber.log.Timber
import javax.inject.Inject

class WeatherDetailsFragment : Fragment(), WeatherContract.View {

	@Inject lateinit var presenter : WeatherContract.UserActionsListener

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_weather_details, container, false)
		SWApplication.get(view.context).applicationComponent().inject(this)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		weatherIcon.setOnClickListener{ presenter.updateWeather() }
		presenter.bindView(this)
	}

	override fun onResume() {
		super.onResume()
		presenter.updateWeather()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		presenter.unbindView()
	}

	override fun showDate(date: String) {
		txtDate.text = date
	}

	override fun showTemperature(temp: String) {
		txtTemp.text = temp
	}

	override fun showWind(wind: String) {
		txtWind.text = wind
	}

	override fun showHumidity(humidity: String) {
		txtHumidity.text = humidity
	}

	override fun showPressure(pressure: String) {
		txtPressure.text = pressure
	}

	override fun showWeatherIcon(url: String) {
		Timber.v("showWeatherIcon %s", url)
		Glide.with(context)
				.load(url)
				.into(weatherIcon)
	}

	override fun showProgress() {
		progress.visibility = View.VISIBLE
	}

	override fun hideProgress() {
		progress.visibility = View.GONE
	}

	override fun showError(message: String) {
		Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
	}

	override fun showError(resId: Int) {
		Snackbar.make(container, resId, Snackbar.LENGTH_LONG).show()
	}
}