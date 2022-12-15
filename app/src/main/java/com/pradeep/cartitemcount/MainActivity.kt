package com.pradeep.cartitemcount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.cartitemcount.ui.theme.CartItemCountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartItemCountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimateItemCount()
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateItemCount() {

    var count by remember{
        mutableStateOf(0)
    }
    
    Column(
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //incr button
        Button(onClick = { count++ }) {
            Text(text = " + ",Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))


        AnimatedContent(
            targetState = count,
            //transition animation logic
            transitionSpec = {
                //anim for final value
                //anim for initial value
                if (targetState>initialState){
                    slideInVertically { fullHeight -> fullHeight }+ fadeIn() with
                            slideOutVertically { fullHeight -> -fullHeight }+ fadeOut()
                }else{
                    slideInVertically{fullHeight -> -fullHeight}+ fadeIn() with
                    slideOutVertically { fullHeight -> fullHeight }+ fadeOut()
                }.using(
                    SizeTransform(clip=false)
                )
            }

            ) {
            Box(
                modifier = Modifier.size(180.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = "$count", style= MaterialTheme.typography.h1)
            }
        }


        //decr button
        Button(onClick = { count-- }) {
            Text(text = " - ",Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))

        

        

    }

    

}


