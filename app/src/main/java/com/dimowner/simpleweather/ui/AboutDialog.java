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

package com.dimowner.simpleweather.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dimowner.simpleweather.R;

/**
 * Dialog shows information about application.
 * @author Dimowner
 */
public class AboutDialog extends DialogFragment {

	private static final String VERSION_UNAVAILABLE = "N/A";
	private static final int REVEAL_DURATION = 600; //mils

	public AboutDialog() {
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Get app version
		PackageManager pm = getActivity().getPackageManager();
		String packageName = getActivity().getPackageName();
		String versionName;
		try {
			PackageInfo info = pm.getPackageInfo(packageName, 0);
			versionName = info.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			versionName = VERSION_UNAVAILABLE;
		}

		// Build the about body view and append the link to see OSS licenses
		SpannableStringBuilder aboutBody = new SpannableStringBuilder();
		aboutBody.append(Html.fromHtml(getString(R.string.about_body, versionName)));

		LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.dialog_about, null);

		TextView content = view.findViewById(R.id.about_txt_content);
		content.setText(aboutBody);

		return new AlertDialog.Builder(getActivity())
				.setTitle(R.string.nav_about)
				.setView(view)
				.setPositiveButton(R.string.btn_ok, (dialog, whichButton) -> dismiss())
				.create();
	}

	@Override
	public void onDismiss(final DialogInterface dialog) {
		super.onDismiss(dialog);
		final Activity activity = getActivity();
		if (activity instanceof DialogInterface.OnDismissListener) {
			((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
		}
	}
}
