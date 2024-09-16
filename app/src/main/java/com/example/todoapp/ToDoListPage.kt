package com.example.todoapp

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoListPage(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel
) {

    val todoList by viewModel.toDoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    var viewState by remember { mutableStateOf("All") }

    val colorsBackground = listOf(
        Color(0xFF003356),
        Color(0xFF001A2C)
    )

    val colorContainerStateSelected = Color(0xFFF4FAFE)
    val colorTextStateSelected = Color(0xFF001523)

    val colorContainerStateNotSelected = Color(0xFF00253E)
    val colorTextStateNotSelected = Color.White



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colorsBackground))
            .padding(8.dp)
            .padding(vertical = 25.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 5.dp
                ),
            singleLine = true,

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF00406C),
                unfocusedContainerColor = Color(0xFF00406C),

                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,

                focusedTextColor = Color.White

            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            placeholder = {
                Text(
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color(0x8F2E6B96),
                        fontWeight = FontWeight.Bold
                    ),
                    text = "To Do..."
                )
            },
            shape = RoundedCornerShape(20.dp),
            value = inputText,
            onValueChange = {
                inputText = it
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(45.dp, 40.dp),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF00253E)),
                    onClick = {
                        viewModel.addToDo(inputText)
                        inputText = ""
                    }
                ) {
                    Box {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.add_icon),
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))


        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (viewState == "Completed") {
                                colorContainerStateSelected
                            } else {
                                colorContainerStateNotSelected
                            },
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable(onClick = {
                            viewState = "Completed"
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF001523),
                                    shape = CircleShape
                                )
                                .size(20.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = viewModel.getCountIsCompleted().toString(),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            )
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = if (viewState == "Completed") {
                                    colorTextStateSelected
                                } else {
                                    colorTextStateNotSelected
                                },
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            ),
                            text = "Concluído"
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (viewState == "Incomplete") {
                                colorContainerStateSelected
                            } else {
                                colorContainerStateNotSelected
                            },
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable(onClick = {
                            viewState = "Incomplete"
                        }),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF001523),
                                    shape = CircleShape
                                )
                                .size(20.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = viewModel.getCountIsNotCompleted().toString(),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            )
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = if (viewState == "Incomplete") {
                                    colorTextStateSelected
                                } else {
                                    colorTextStateNotSelected
                                },
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            ),
                            text = "Pendente"
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (viewState == "All") {
                                colorContainerStateSelected
                            } else {
                                colorContainerStateNotSelected
                            },
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable(onClick = {
                            viewState = "All"
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF001523),
                                    shape = CircleShape
                                )
                                .size(20.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = viewModel.getCountAll().toString(),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            )
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = if (viewState == "All") {
                                    colorTextStateSelected
                                } else {
                                    colorTextStateNotSelected
                                },
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            ),
                            text = "Todos"
                        )
                    }
                }
            }
        }




        todoList?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
                content = {
                    itemsIndexed(it) { index, item ->

                        when (viewState) {
                            "All" -> {
                                ToDoListItem(
                                    item = item,
                                    onDeleteClick = {
                                        viewModel.deleteToDo(item.id)
                                    },
                                    onCheckedClick = {
                                        viewModel.onCheckedClick(item)
                                    }
                                )
                            }

                            "Completed" -> {
                                if (item.isCompleted) {
                                    ToDoListItem(
                                        item = item,
                                        onDeleteClick = {
                                            viewModel.deleteToDo(item.id)
                                        },
                                        onCheckedClick = {
                                            viewModel.onCheckedClick(item)
                                        }
                                    )
                                }
                            }

                            "Incomplete" -> {
                                if (!item.isCompleted) {
                                    ToDoListItem(
                                        item = item,
                                        onDeleteClick = {
                                            viewModel.deleteToDo(item.id)
                                        },
                                        onCheckedClick = {
                                            viewModel.onCheckedClick(item)
                                        }
                                    )
                                }
                            }
                        }


                    }
                }
            )
        } ?: Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = R.drawable.happy_icon),
                contentDescription = null,
                tint = Color(0x61FFFFFF)
            )
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Lista Vazia",
                color = Color(0x61FFFFFF),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun ToDoListItem(
    modifier: Modifier = Modifier,
    item: ToDoModel,
    onDeleteClick: () -> Unit,
    onCheckedClick: () -> Unit
) {


    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFF00253E),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Checkbox(
            checked = item.isCompleted,
            onCheckedChange = {
                onCheckedClick()
            }
        )


        Text(
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 18.sp,
            text = item.toDo,
            color = Color.White
        )

        IconButton(
            modifier = Modifier.padding(end = 15.dp),
            onClick = { onDeleteClick() }
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(R.drawable.delete_icon),
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Apppreview() {
    ToDoListPage(viewModel = ToDoViewModel())
}