package kz.veter420.android_modern.presentation.pages.calc

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
fun CalcScreen(
	navController: NavController,
	mViewModel: CalcViewModel = koinViewModel()
) {
	Column {
		CalcScreenContent(
			navController = navController,
			mViewModel = mViewModel
		)
	}
}


@Composable
fun CalcScreenContent(navController: NavController, mViewModel: CalcViewModel) {
	val state by mViewModel.state.collectAsState()
	val result = remember(state) { mutableStateOf(state.result) }
	val errorState = remember(state) { mutableStateOf(state.error) }

	AlertContent(errorState)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(0.dp, 0.dp)
	) {
		TextToolbar(
			text = stringResource(id = R.string.calc),
			showBackButton = false,
			onClickBack = {
				navController.popBackStack()
			}
		)
		Column(
			modifier = Modifier.fillMaxWidth().padding(16.dp)
		) {
			val input1State = remember { mutableStateOf(TextFieldValue()) }
			val input2State = remember { mutableStateOf(TextFieldValue()) }

			Spacer(modifier = Modifier.height(12.dp))

			ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
				val (field1, operand, field2) = createRefs()
				CommonTextField(
					modifier = Modifier
						.testTag("inputField1")
						.constrainAs(field1) {
							start.linkTo(parent.start)
							width = Dimension.fillToConstraints
							end.linkTo(field2.start, margin = 20.dp)
						},
					input = input1State,
					placeholder = "Field 1",
					singleLine = true,
					keyboardType = KeyboardType.Number
				)

				Text(
					text = "x",
					modifier = Modifier
						.wrapContentSize()
						.constrainAs(operand) {
							top.linkTo(parent.top)
							bottom.linkTo(parent.bottom)
							start.linkTo(field1.end)
							end.linkTo(field2.start)
						},
					textAlign = TextAlign.Center
				)

				CommonTextField(
					modifier = Modifier
						.testTag("inputField2")
						.constrainAs(field2) {
							start.linkTo(field1.end, margin = 20.dp)
							end.linkTo(parent.end)
							width = Dimension.fillToConstraints
						},
					input = input2State,
					placeholder = "Field 2",
					singleLine = true,
					keyboardType = KeyboardType.Number
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			CommonButton(text = "=", onClick = {
				mViewModel.calculate(input1State.value.text, input2State.value.text)
			})

			Spacer(modifier = Modifier.height(16.dp))

			OutlinedText(
				text = result.value,
				modifier = Modifier
					.semantics(mergeDescendants = true, properties = {})
					.testTag("calcOutput")
			)

			Spacer(modifier = Modifier.height(16.dp))

			CommonButton(
				modifier = Modifier.fillMaxWidth(),
				text = "C",
				onClick = {
					input1State.value = TextFieldValue()
					input2State.value = TextFieldValue()
					mViewModel.clear()
				}
			)

		}
	}

}