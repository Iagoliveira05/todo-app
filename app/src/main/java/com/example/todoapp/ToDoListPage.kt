package com.example.todoapp

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(vertical = 20.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 5.dp
                ),
            value = inputText,
            onValueChange = {
                inputText = it
            },
            trailingIcon = {
                Button(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = {
                        viewModel.addToDo(inputText)
                        inputText = ""
                    }
                ) {
                    Text(text = "Add")
                }
            },
            label = { Text(text = "To Do") }
        )
        Spacer(modifier = Modifier.height(15.dp))


        todoList?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                content = {
                    itemsIndexed(it) { index, item ->
                        ToDoListItem(
                            item = item,
                            onDeleteClick = {
                                viewModel.deleteToDo(item.id)
                            }
                        )
                    }
                }
            )
        }?: Text(
            text = "No item yet",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )



    }
}


@Composable
fun ToDoListItem(
    modifier: Modifier = Modifier,
    item: ToDoModel,
    onDeleteClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color(0xFF54A0B3), shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 15.sp,
            text = item.toDo,
            color = Color.White
        )

        IconButton(onClick = { onDeleteClick() }) {
            Icon(
                painter = painterResource(R.drawable.delete_icon),
                contentDescription = null
            )
        }
    }
}