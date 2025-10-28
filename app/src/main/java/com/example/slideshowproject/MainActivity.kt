package com.example.slideshowproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontListFontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshowproject.ui.theme.SlideShowProjectTheme
import kotlin.compareTo
import kotlin.dec
import kotlin.inc

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideShowProjectTheme {
                    SlideShowImages(Modifier.padding(8.dp))
                }
            }
        }
    }


@Composable
fun SlideShowImages(modifier: Modifier = Modifier) {
    var picNumber by remember {mutableStateOf(1)}
    val imageResource= when(picNumber){
        1->R.drawable.brooklyn
        2->R.drawable.central_park
        3->R.drawable.hudson_yards
        4->R.drawable.grand_central
        5->R.drawable.new_york_public_library
        6->R.drawable.statue_of_liberty
        7->R.drawable.world_trade_center
        else ->R.drawable.little_island
    }
    var textinput by remember { mutableStateOf("") }  // state variable for input
    Column(modifier = Modifier.statusBarsPadding()
        .padding(horizontal = 40.dp)
        .verticalScroll(rememberScrollState())
        .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text= "New York's Attractions",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp

        )
        Image(
            painter= painterResource(imageResource),
            contentDescription = null
        )
        ImageDescription(picNumber)
        Spacer(modifier=Modifier.height(16.dp))
        Row(modifier=Modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (picNumber <9 && picNumber>1) {
                    picNumber--


                } else{
                    picNumber = 8
                }
            }) {
                Text(stringResource(R.string.previous))
            }
            Spacer(modifier.width(10.dp))
            Button(onClick = {
                if (picNumber <8) {
                    picNumber++
                } else{
                    picNumber = 1
                }
            }) {
                Text(stringResource(R.string.next))
            }
            }
        Spacer(modifier.width(10.dp))
        Text(
            text="Search Image",
            modifier= Modifier.align(Alignment.Start ))

        TextField(
            value = textinput,
            onValueChange = { textinput=it},
            label = { Text("Type a Number Between 1 and 8") },
            singleLine = true,
        )
        var input= textinput.toIntOrNull()?: 1
        Button(onClick = {
            if(input<9 && input>0){
            picNumber=input}
            else{
                picNumber=1
            }


        }) {
            Text(stringResource(R.string.go))
        }

    }
}

@Composable
fun ImageDescription(picNumber: Int){

var number by remember { mutableStateOf(0) }
  number= when(picNumber)
   {  1->R.string.Bridge
      2->R.string.park
      3->R.string.hudson
      4->R.string.central
      5->R.string.library
      6->R.string.statue
      7->R.string.trade
      else ->R.string.island
   }
  Text(
      text= stringResource(id= number),
      fontWeight = FontWeight.Bold
  )


}
@Preview(showBackground = true)
@Composable
fun SlideShowPreview() {
    SlideShowProjectTheme {
        SlideShowImages(Modifier)
    }
}