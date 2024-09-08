package pro.sparrow_team.qq.ui.screens.main

import android.app.Activity
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetLayout(
    bottomSheetContents: BottomSheetContents,
    sheetState: BottomSheetScaffoldState,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel,
) {

    val context = LocalContext.current as Activity


    /**
     * show the correct layout based on bottomSheetContents
     * the reason i put NONE is for null pointer exceptions
     * so NONE is just a place holder to prevent null exceptions
     */

    when (bottomSheetContents) {
        BottomSheetContents.SERVER_LIST -> ServerBottomSheetContent(
            mainViewModel.decodedConfigList.value,
            mainViewModel,
            dataStoreViewModel,
            sheetState
        )

        BottomSheetContents.UPDATE -> UpdateBottomSheetContent(sheetState, context, mainViewModel)
        BottomSheetContents.NOTIFICATION -> NotificationBottomSheetContent(context)
        BottomSheetContents.DISABLED_USER -> DisabledUserBottomSheetContent(
            context = context,
            mainViewModel = mainViewModel
        )

        BottomSheetContents.UNDER_CONSTRUCTION -> UnderConstructionBottomSheetContent(
            context,
            mainViewModel
        )

        BottomSheetContents.NONE -> {
            Text(text = "PlaceHolder")
        }
    }
}