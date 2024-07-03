

package com.example.animation7

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.round

@Composable
fun AnimationPage() {

    val scale = remember {
        Animatable(0f)
    }

    var animateAgain by remember {
        mutableStateOf(false)
    }
    
    LaunchedEffect(key1 = false ) {
        scale.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Picture",
            modifier = Modifier
                .size(300.dp)
                .weight(1f)
                .rotate(scale.value)
        )
        Column(
            modifier = Modifier.padding(bottom = 70.dp)
        ) {


        Button(onClick = {
                GlobalScope.launch() {
                    scale.snapTo(0f)
                }
                animateAgain = !animateAgain
            }
        ) {
            Text(text = "Animate")
            }
        }
    }
}


