package com.pierretest.firstprgapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pierretest.firstprgapp.data.database.CustomerModelEntity
import com.pierretest.firstprgapp.data.network.ApiResponse
import com.pierretest.firstprgapp.viewmodels.CustomersViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun CustomerScreen() {

    Column(
        modifier = Modifier
    ) {
        AddCustomer()
        Divider()
        DisplayCustomers()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomer(viewModel: CustomersViewModel = hiltViewModel()) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    //Local state for success and error messages

    val insertCustomerState by viewModel.insertCustomerState.collectAsState()
    var showsnackbar by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(insertCustomerState) {
        when (insertCustomerState) {
            is ApiResponse.Success -> {
                showToast("Customer added Successfully!", context)
                showsnackbar = true
            }

            is ApiResponse.Error -> {
                val errorMessage = (insertCustomerState as ApiResponse.Error).message
                showToast("Error : $errorMessage", context)
                showsnackbar = true

            }

            is ApiResponse.Loading -> {

            }

        }
    }


    Column(
        modifier = Modifier
    ) {

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") }
        )

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = "Last Name") }
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )


        Button(onClick = {
            val newCustomer = CustomerModelEntity(
                firstName = firstName,
                lastName = lastName,
                email = email
            )

            viewModel.insertCustomer(newCustomer)

        }) {

            Text(text = "Add Customer")
        }

        if (showsnackbar) {
            Snackbar(
                action = {
                    // You can customize the action button if needed
                    TextButton(onClick = {
                        showsnackbar = false
                    }) {
                        Text("OK")
                    }
                }
            ) {
                when (insertCustomerState) {
                    is ApiResponse.Success -> {
                        Text("Customer added successfully!")
                    }

                    is ApiResponse.Error -> {
                        val errorMessage = (insertCustomerState as ApiResponse.Error).message
                        Text("Error: $errorMessage")
                    }

                    else -> {
                        Text("Unknown error")
                    }
                }
            }
        }

    }

}

private fun showToast(message: String, context: android.content.Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayCustomers(
    viewModel: CustomersViewModel = hiltViewModel()
) {
    
    LaunchedEffect(viewModel) {
        viewModel.getCustomers()
    }

    val customers by viewModel.listCustomers.collectAsState()

    when(customers) {
        is ApiResponse.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(200, 200, 200, 100))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(Color(200, 200, 200, 100))
                        .align(Alignment.Center),
                    color = Color.Red
                )
            }
        }
        is ApiResponse.Success -> {
            val customerList = (customers as ApiResponse.Success<List<CustomerModelEntity>>).data



            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                stickyHeader(
                    key = null,
                    contentType = null,
                    content = {
                        Column(
                            modifier = Modifier
                        ) {
                            Row (modifier = Modifier
                                .background(Color(73, 65, 65, 220))
                                .padding(8.dp)
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Total : ${customerList.size} customers",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .background(Color(73, 65, 65, 220))
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = "First Name",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    modifier = Modifier.weight(0.4f)
                                )
                                Text(
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    text = "Last Name",
                                    modifier = Modifier.weight(0.3f)
                                )
                                Text(
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    text = "E-mail",
                                    modifier = Modifier.weight(0.4f)
                                )
                            }
//                            Spacer(modifier = Modifier.height(50.dp))
                        }

                    }
                )
                items(customerList) {customer ->
                    CustomerItem(customer = customer) {
                        viewModel.deleteCustomer(customer)
                        viewModel.getCustomers()
                    }
                }
            }
        }
        is ApiResponse.Error -> {
            val errorMessage = (customers as ApiResponse.Error).message
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            ) {
                Text(
                    text = errorMessage ?: "Unexpected Error",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }


    

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerItem(customer: CustomerModelEntity,
                 onDeleteItem:()->Unit) {

    val viewModel :CustomersViewModel = hiltViewModel()
    val deleteCustomerState by viewModel.deleteCustomerState.collectAsState()

    var showsnackBar by remember { mutableStateOf(false) }
    val context = LocalContext.current

    when(deleteCustomerState) {
        is ApiResponse.Error -> {
            val errorMessage = (deleteCustomerState as ApiResponse.Error).message
            showToast("Error : $errorMessage", context)
//            showsnackBar = true
            /*        Snackbar(
                        action = {
                            TextButton(onClick = {  }) {
                                Text(text = "OK")
                            }
                        }
                    ) {
                        Text(text = "Error ${(deleteCustomerState as ApiResponse.Error).message}")
                    }*/
        }
        is ApiResponse.Success -> {
            showToast("Customer deleted Successfully !", context)
//            showsnackBar = true
            /*            Snackbar(
                            action = {
                                TextButton(onClick = { }) {
                                    Text(text = "OK")
                                }
                            }
                        ) {
                            Text(text = "Customer deleted Successfully!")
                        }*/
        }
        else -> {

        }
    }

    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Surface(
            onClick = {}
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(200, 200, 200, 255)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = customer.firstName, modifier = Modifier.weight(0.3f))
                Text(text = customer.lastName, modifier = Modifier.weight(0.3f))
                Text(text = customer.email, modifier = Modifier.weight(0.3f))
                IconButton(
                    onClick = onDeleteItem,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.weight(0.1f),
                        imageVector = Icons.Default.Delete, contentDescription = null
                    )

                }
            }
        }


    }


}

