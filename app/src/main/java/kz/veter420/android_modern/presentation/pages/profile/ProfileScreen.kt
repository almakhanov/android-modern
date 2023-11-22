package kz.veter420.android_modern.presentation.pages.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.pages.alert.AlertContent
import kz.veter420.android_modern.presentation.ui.components.CommonButton
import kz.veter420.android_modern.presentation.ui.components.CommonTextField
import kz.veter420.android_modern.presentation.ui.components.OutlinedText
import kz.veter420.android_modern.presentation.ui.components.TextToolbar
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
	navController: NavController,
	mViewModel: ProfileViewModel = koinViewModel()
) {
	Column {
		ProfileScreenContent(
			navController = navController,
			mViewModel = mViewModel
		)
	}
}


@Composable
fun ProfileScreenContent(navController: NavController, mViewModel: ProfileViewModel) {
	val state by mViewModel.state.collectAsState()
	val errorState = remember(state) { mutableStateOf(state.error) }

	AlertContent(errorState)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(0.dp, 0.dp)
	) {
		TextToolbar(
			text = stringResource(id = R.string.profile),
			showBackButton = false,
			onClickBack = {
				navController.popBackStack()
			}
		)
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			val input1State = remember { mutableStateOf(TextFieldValue()) }
			val input2State = remember { mutableStateOf(TextFieldValue()) }
			val result = remember { mutableStateOf("?") }

			Spacer(modifier = Modifier.height(12.dp))

			CommonTextField(
				modifier = Modifier.fillMaxWidth(),
				input = input1State,
				placeholder = "Field 1",
				singleLine = true,
				keyboardType = KeyboardType.Number
			)

			Spacer(modifier = Modifier.height(12.dp))

			Text(text = "+", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

			Spacer(modifier = Modifier.height(12.dp))

			CommonTextField(
				modifier = Modifier.fillMaxWidth(),
				input = input2State,
				placeholder = "Field 2",
				singleLine = true,
				keyboardType = KeyboardType.Number
			)
			Spacer(modifier = Modifier.height(16.dp))

			CommonButton(text = "Result", onClick = {
				result.value = (input1State.value.text.toInt() + input2State.value.text.toInt()).toString()
			})

			Spacer(modifier = Modifier.height(16.dp))

			OutlinedText(text = result.value)
		}
	}

}