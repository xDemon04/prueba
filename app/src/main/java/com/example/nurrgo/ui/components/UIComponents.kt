package com.example.nurrgo.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.RoundedCornerShape // Importar de Material3
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurrgo.ui.theme.AppColors
import com.example.nurrgo.ui.theme.AppShapes
import com.example.nurrgo.ui.theme.AppSpacing
import com.example.nurrgo.ui.theme.AppTypography
import androidx.compose.material3.Shape // Importar Shape de Material3

// Enhanced Button Component
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Medium,
    icon: ImageVector? = null,
    isLoading: Boolean = false
) {
    val buttonColors = when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.buttonColors(
            containerColor = AppColors.Primary,
            disabledContainerColor = AppColors.OnSurfaceVariant.copy(alpha = 0.12f)
        )
        ButtonVariant.Secondary -> ButtonDefaults.buttonColors(
            containerColor = AppColors.Secondary,
            disabledContainerColor = AppColors.OnSurfaceVariant.copy(alpha = 0.12f)
        )
        ButtonVariant.Outlined -> ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
        ButtonVariant.Text -> ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
        ButtonVariant.Success -> ButtonDefaults.buttonColors(
            containerColor = Color(0xFF10B981), // Green
            disabledContainerColor = AppColors.OnSurfaceVariant.copy(alpha = 0.12f)
        )
        ButtonVariant.Error -> ButtonDefaults.buttonColors(
            containerColor = AppColors.Error,
            disabledContainerColor = AppColors.OnSurfaceVariant.copy(alpha = 0.12f)
        )
    }

    val buttonHeight = when (size) {
        ButtonSize.Small -> 36.dp
        ButtonSize.Medium -> 48.dp
        ButtonSize.Large -> 56.dp
    }

    val textStyle = when (size) {
        ButtonSize.Small -> AppTypography.labelLarge.copy(fontSize = 12.sp)
        ButtonSize.Medium -> AppTypography.labelLarge.copy(fontSize = 14.sp)
        ButtonSize.Large -> AppTypography.labelLarge.copy(fontSize = 16.sp)
    }

    val border = if (variant == ButtonVariant.Outlined) {
        BorderStroke(1.dp, AppColors.Primary)
    } else null

    when (variant) {
        ButtonVariant.Outlined -> OutlinedButton(
            onClick = onClick,
            enabled = enabled && !isLoading,
            colors = buttonColors as ButtonColors,
            shape = AppShapes.medium,
            border = border,
            modifier = modifier.height(buttonHeight)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AppColors.Primary,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = AppColors.Primary
                        )
                        Spacer(modifier = Modifier.width(AppSpacing.sm))
                    }
                    Text(
                        text = text,
                        style = textStyle,
                        color = AppColors.Primary
                    )
                }
            }
        }
        ButtonVariant.Text -> TextButton(
            onClick = onClick,
            enabled = enabled && !isLoading,
            colors = buttonColors as ButtonColors,
            shape = AppShapes.medium,
            modifier = modifier.height(buttonHeight)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AppColors.Primary,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = AppColors.Primary
                        )
                        Spacer(modifier = Modifier.width(AppSpacing.sm))
                    }
                    Text(
                        text = text,
                        style = textStyle,
                        color = AppColors.Primary
                    )
                }
            }
        }
        else -> Button(
            onClick = onClick,
            enabled = enabled && !isLoading,
            colors = buttonColors,
            shape = AppShapes.medium,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            modifier = modifier.height(buttonHeight)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AppColors.OnPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = AppColors.OnPrimary
                        )
                        Spacer(modifier = Modifier.width(AppSpacing.sm))
                    }
                    Text(
                        text = text,
                        style = textStyle,
                        color = AppColors.OnPrimary
                    )
                }
            }
        }
    }
}

enum class ButtonVariant {
    Primary, Secondary, Outlined, Text, Success, Error
}

enum class ButtonSize {
    Small, Medium, Large
}

// Enhanced Card Component
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    shape: Shape = AppShapes.medium, // Usar el tipo explícito de Material3.Shape
    containerColor: Color = AppColors.Surface,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = elevation,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        content()
    }
}

// Enhanced TextField Component
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = label?.let { { Text(it) } },
            placeholder = placeholder?.let { { Text(it) } },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (isError) AppColors.Error else AppColors.OnSurfaceVariant
                    )
                }
            },
            trailingIcon = trailingIcon?.let {
                {
                    IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            tint = if (isError) AppColors.Error else AppColors.OnSurfaceVariant
                        )
                    }
                }
            },
            isError = isError,
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppColors.Surface,
                unfocusedContainerColor = AppColors.Surface,
                errorContainerColor = AppColors.Surface,
                focusedBorderColor = AppColors.BorderFocused,
                unfocusedBorderColor = AppColors.Border,
                errorBorderColor = AppColors.Error,
                cursorColor = AppColors.Primary,
                focusedLabelColor = AppColors.Primary,
                unfocusedLabelColor = AppColors.OnSurfaceVariant,
                errorLabelColor = AppColors.Error
            ),
            shape = AppShapes.medium,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = AppColors.Error,
                style = AppTypography.bodyMedium,
                modifier = Modifier.padding(start = AppSpacing.sm, top = AppSpacing.xs)
            )
        }
    }
}

// Loading Component
@Composable
fun AppLoadingIndicator(
    modifier: Modifier = Modifier,
    size: LoadingSize = LoadingSize.Medium,
    color: Color = AppColors.Primary
) {
    val indicatorSize = when (size) {
        LoadingSize.Small -> 20.dp
        LoadingSize.Medium -> 32.dp
        LoadingSize.Large -> 48.dp
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(indicatorSize),
            color = color,
            strokeWidth = 3.dp
        )
    }
}

enum class LoadingSize {
    Small, Medium, Large
}

// Status Card Component
@Composable
fun StatusCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = AppColors.Surface,
    iconColor: Color = AppColors.Primary,
    onClick: (() -> Unit)? = null
) {
    val cardModifier = if (onClick != null) {
        modifier.clickable { onClick() }
    } else {
        modifier
    }

    AppCard(
        modifier = cardModifier,
        containerColor = containerColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        iconColor.copy(alpha = 0.1f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(AppSpacing.md))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = AppTypography.bodyMedium,
                    color = AppColors.OnSurfaceVariant
                )
                Text(
                    text = value,
                    style = AppTypography.titleLarge,
                    color = AppColors.OnSurface,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// Gradient Background Component
@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppColors.GradientStart,
                        AppColors.GradientEnd
                    )
                )
            )
    ) {
        content()
    }
}

// Animated Icon Button
@Composable
fun AnimatedIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    tint: Color = AppColors.OnSurface,
    backgroundColor: Color = Color.Transparent,
    size: Int = 24
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(300) // Using a fixed duration for now
    )

    IconButton(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = modifier
            .scale(scale)
            .background(backgroundColor, CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size.dp)
        )
    }
}

// Section Header Component
@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    action: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.md, vertical = AppSpacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                style = AppTypography.titleLarge,
                color = AppColors.OnSurface,
                fontWeight = FontWeight.SemiBold
            )
            subtitle?.let {
                Text(
                    text = it,
                    style = AppTypography.bodyMedium,
                    color = AppColors.OnSurfaceVariant
                )
            }
        }
        action?.invoke()
    }
}

// Empty State Component
@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    subtitle: String,
    action: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppSpacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppColors.OnSurfaceVariant,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(AppSpacing.md))

        Text(
            text = title,
            style = AppTypography.titleLarge,
            color = AppColors.OnSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppSpacing.sm))

        Text(
            text = subtitle,
            style = AppTypography.bodyMedium,
            color = AppColors.OnSurfaceVariant,
            textAlign = TextAlign.Center
        )

        action?.let {
            Spacer(modifier = Modifier.height(AppSpacing.lg))
            it()
        }
    }
}
