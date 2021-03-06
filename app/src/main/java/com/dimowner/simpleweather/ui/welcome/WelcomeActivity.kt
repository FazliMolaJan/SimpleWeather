package com.dimowner.simpleweather.ui.welcome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.dimowner.simpleweather.R
import com.dimowner.simpleweather.SWApplication
import com.dimowner.simpleweather.domain.metrics.MetricsContract
import com.dimowner.simpleweather.domain.welcome.WelcomePresenter
import com.dimowner.simpleweather.ui.location.LocationActivity
import com.dimowner.simpleweather.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject

class WelcomeActivity : Activity(), MetricsContract.View {

	@Inject lateinit var presenter : WelcomePresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_welcome)
		SWApplication.get(applicationContext).applicationComponent().inject(this)

		txtWind.setOnClickListener{ presenter.switchWind() }
		txtTempFormat.setOnClickListener{ presenter.switchTemperature() }
		txtPressure.setOnClickListener{ presenter.switchPressure() }
		txtTimeFormat.setOnClickListener{ presenter.switchTimeFormat() }
		btnApply.setOnClickListener {
			presenter.firstRunExecuted()
			startActivity(Intent(applicationContext, LocationActivity::class.java))
			finish()
		}

		presenter.bindView(this)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.unbindView()
	}

	override fun showTemperatureFormat(format : String) {
		txtTempFormat.text = format
	}

	override fun showWindFormat(format : String) {
		txtWind.text = format
	}

	override fun showPressureFormat(format : String) {
		txtPressure.text = format
	}

	override fun showTimeFormat(format : String) {
		txtTimeFormat.text = format
	}

	override fun showProgress() {}
	override fun hideProgress() {}
	override fun showError(message: String) {}
	override fun showError(resId: Int) {}

}
