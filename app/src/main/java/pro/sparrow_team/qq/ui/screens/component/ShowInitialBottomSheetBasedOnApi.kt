package pro.sparrow_team.qq.ui.screens.component

import android.content.Context
import android.util.Log
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import pro.sparrow_team.qq.model.mainscreen.Status
import pro.sparrow_team.qq.model.mainscreen.testStatus
import pro.sparrow_team.qq.ui.screens.main.BottomSheetContents
import pro.sparrow_team.qq.ui.screens.main.isUpdateAvailable
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowInitialBottomSheetBasedOnApi(
    mainViewModel: MainViewModel,
    sheetState:BottomSheetScaffoldState,
    context: Context,
    dataStoreViewModel: DataStoreViewModel
) {

    /**
     * this part is for showing bottom sheet based on API
     * if we have an update the the UPDATE bottom sheet is shown or if
     * we are not functional UNDER_CONSTRUCTION will show up.
     * the reason we have mainViewModel.showInitialBottomSheetComplete.value
     * is because if this launched effect is run only once we do not see any bottom sheet for some reason
     * so i have this variable and a slight delay to actually be able to show the bottom sheet
     * The first time the showInitialBottomSheetComplete is false and then it becomes true
     * and the launched effect will run again if the showInitialBottomSheetComplete is changed
     * and then we can see the bottom sheet.
     * this part has some bugs if a couple of conditions are true. and also it is complicated.
     * Change if found a better way
     */

    if (mainViewModel.isApiHealthy) {
        LaunchedEffect(mainViewModel.showInitialBottomSheetComplete.value) {
//            Log.e("TAG", mainViewModel.bottomSheetContents.value.name ?: "")

            if (isUpdateAvailable(mainViewModel, context)) {
                mainViewModel.bottomSheetContents.value = BottomSheetContents.UPDATE
                sheetState.bottomSheetState.show()
            }

            if (!mainViewModel.status.functional) {
                mainViewModel.bottomSheetContents.value =
                    BottomSheetContents.UNDER_CONSTRUCTION
                sheetState.bottomSheetState.show()
            }

            if (!dataStoreViewModel.getRegisterState()){
                if (mainViewModel.status.reject) {
                    mainViewModel.bottomSheetContents.value = BottomSheetContents.DISABLED_USER
                    sheetState.bottomSheetState.show()
                }
            }

            if (mainViewModel.bottomSheetContents.value == BottomSheetContents.NONE){
                mainViewModel.showInitialBottomSheetComplete.value = true
            }
            delay(500)
            mainViewModel.showInitialBottomSheetComplete.value = true
        }
    } else {
        LaunchedEffect(mainViewModel.showInitialBottomSheetComplete.value) {
            mainViewModel.bottomSheetContents.value =
                BottomSheetContents.UNDER_CONSTRUCTION
            sheetState.bottomSheetState.show()
            delay(500)
            mainViewModel.showInitialBottomSheetComplete.value = true
        }
    }
}