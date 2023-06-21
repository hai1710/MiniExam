function showAll(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api",
        success: function (data) {
            console.log(data);

            let picture = "";
            picture += `<table border="1px">
                            <tr>
                                 <th>ID</th>    
                                 <th>Name</th>
                                 <th>Height</th>
                                 <th>Width</th>
                                 <th>material</th>
                                 <th>description</th>
                                 <th>price</th>
                                 <th>category List</th>
                               
                            </tr>`;
            for (let i = 0; i < data.length; i++){
                picture += `<tr>
                                <td>${data[i].id}</td>
                                <td>${data[i].name}</td>
                                <td>${data[i].height_size}</td>
                                <td>${data[i].width_size}</td>
                                <td>${data[i].material}</td>
                                <td>${data[i].description}</td>
                                <td>${data[i].price}</td>
                            <td>`;
                for (let j = 0; j < data[i].categoryList.length; j++) {
                    picture += `${data[i].categoryList[j].name_category}`;

                    if (j < data[i].categoryList.length - 1) {
                        picture += ", ";
                    }
                }
                picture += `</td></tr>`
            }
            picture += `</table>`;
            document.getElementById("display").innerHTML = picture;
        }
    })
}

function showFormCreate(){
    let form = "";
    form += `
    <label>Name: </label>
    <input type="text" id="name"><br>
    <label>Height: </label>
    <input type="number" id="height_size"><br>
    <label>Width: </label>
    <input type="number" id="width_size"><br>
    <label>Material: </label>
    <input type="text" id="material"><br>
    <label>Description: </label>
    <input type="text" id="description"><br>
    <label>Price: </label>
    <input type="number" id="price"><br>
    <label>Category List: </label>`

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/category",
        success: function (categoryList){
            console.log(categoryList);
            for( let i = 0; i < categoryList.length; i++){
                const categoryId = categoryList[i].id;
                const categoryName = categoryList[i].name_category;
                const isCheck = "";
                form += `
                <input type="checkbox" name="selectedCategory" 
                value="${categoryId} ${isCheck}">
                <label>${categoryName}</label>`;
            }
            form += `<br>
            <button onclick="savePicture()">Save</button>`;
            document.getElementById("display").innerHTML=form;
        }
    });
}
function savePicture(){
    let name = $("#name").val();
    let height_size = $("#height_size").val();
    let width_size = $("#width_size").val();
    let material = $("#material").val();
    let description = $("#description").val();
    let price = $("#price").val();

    let selectedCategory = [];
    let categoryCheckboxes =
        document.querySelectorAll('input[name="selectedCategory"]:checked');

    for(let i =0; i < categoryCheckboxes.length; i++){
        let check = categoryCheckboxes[i];
        let category = {
            id: check.value,
            name_category: check.name
        };
        selectedCategory.push(category);

    }
    let newPicture = {
        name: name,
        height_size: height_size,
        width_size: width_size,
        material: material,
        description: description,
        price: price,
        categoryList: selectedCategory
    };
    $.ajax({
        type: "POST",
        url:" http://localhost:8080/api",
        data: JSON.stringify(newPicture),
        contentType: "application/json",
        success: function () {
            showAll();
        }
    });
}
function showFormSearch() {
    let form = "";
    form += `
    <span><b>Search By Name</b></span><br>
    <input type="text" id = "search">
    <button onclick="searchByName()">Search</button><br>`;
    form += `
    <span><b>Search By category</b></span><br>
    <select id = "id"></select>
    <button onclick="searchByCategory()">Search</button>`;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/category",
        success: function (data) {
            console.log(data)
            let categoryList = "";
            for (let i = 0; i < data.length; i++) {
                categoryList += `<option value="${data[i].id}">${data[i].name_category}</option>`;
            }
            document.getElementById("id").innerHTML = categoryList;
        }
    })
    document.getElementById("display").innerHTML = form;
}
function searchByName() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/picture/search?name=" + search,
        success: function (data) {
            console.log(data);
            let picture = "";
            picture += `
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Height</th>
                    <th>Width</th>
                    <th>Material</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Categories</th>
                    </tr>`;

            for (let i = 0; i < data.length; i++) {
                picture += `<tr>
                    <td>${data[i].id}</td>
                    <td>${data[i].name}</td>
                    <td>${data[i].height_size}</td>
                    <td>${data[i].width_size}</td>
                    <td>${data[i].material}</td>
                    <td>${data[i].description}</td>
                    <td>${data[i].price}</td>
                    <td>`;

                for (let j = 0; j < data[i].categoryList.length; j++) {
                    picture += `${data[i].categoryList[j].name_category}`;

                    if (j < data[i].categoryList.length - 1) {
                        picture += ", ";
                    }
                }

                    picture += `</td></tr>`;
            }

            picture += `</table>`;
            document.getElementById("display").innerHTML = picture;
        }
    });
}
function searchByCategory() {
    let categoryId = document.getElementById("id").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/painting/searchbycategory/" + categoryId,
        success: function (data) {
            console.log(data);
            let picture = "";
            picture += `
            <table border="1px">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Height</th>
                    <th>Width</th>
                    <th>Material</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>CategoryList</th>
                </tr>`
            for (let i = 0; i < data.length; i++) {
                picture += `<tr>
                    <td>${data[i].id}</td>
                    <td>${data[i].name}</td>
                    <td>${data[i].height_size}</td>
                    <td>${data[i].width_size}</td>
                    <td>${data[i].material}</td>
                    <td>${data[i].description}</td>
                    <td>${data[i].price}</td>
                    <td>`;

                for (let j = 0; j < data[i].categoryList.length; j++) {
                    picture += `${data[i].categoryList[j].name_category}`;

                    if (j < data[i].categoryList.length - 1) {
                        picture += ", ";
                    }
                }

                picture += `</td></tr>`;
            }

            picture += `</table>`;
            document.getElementById("display").innerHTML = picture;
        }
    });
}

