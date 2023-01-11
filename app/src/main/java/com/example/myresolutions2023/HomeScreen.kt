package com.example.myresolutions2023

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myresolutions2023.model.Resolution
import com.example.myresolutions2023.model.Resolutions
import com.example.myresolutions2023.ui.theme.Shapes

@Composable
fun ResolutionList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(Resolutions.resolutions) {
            ResolutionItem(resolution = it)
        }
    }
}

@Composable
fun ResolutionItem(
    modifier: Modifier = Modifier,
    resolution: Resolution,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            ResolutionTitle(title = resolution.resolutionNumber)
            Spacer(modifier = Modifier.height(16.dp))
            ResolutionImage(
                image = resolution.resolutionImage,
                description = stringResource(id = R.string.imageDescription),
                onClickImage = { expanded = !expanded},
            )
            if (expanded) {
                ResolutionDescription(description = resolution.resolution)
            }
        }
    }
}

@Composable
fun ResolutionTitle(
    @StringRes title: Int, modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
fun ResolutionImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    description: String,
    onClickImage: () -> Unit,
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier
            .clip(Shapes.medium)
            .clickable(onClick = onClickImage)
    )
}

@Composable
fun ResolutionDescription(@StringRes description: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = description),
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.padding(8.dp),
        textAlign = TextAlign.Center
    )
}