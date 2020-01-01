<%@ page language="java" contentType="text/html; charset=UTF-8" %>  


<script src="/bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.pagination/latest/tui-pagination.js"></script>
<!-- 
<script src="https://uicdn.toast.com/tui-grid/v4.6.0/tui-grid.js"></script>
  -->
<script src="/toast/tui-grid.js"></script> 
 
<link rel="stylesheet" href="https://uicdn.toast.com/tui-grid/v4.8.0/tui-grid.css" />

<style>

#selectBox {
 margin-bottom : 5px

}



</style>

<!-- 
<div class="row">
        <div class="col-lg-6">
            <div class="ibox ">
                <div class="ibox-content text-center p-md">

                    <h4 class="m-b-xxs">Admin</h4>
                    <small>(optional layout)</small>
                    <p>Available configure options</p>
                    <span class="simple_tag">Scroll navbar</span>
                    <span class="simple_tag">Top fixed navbar</span>
                    <span class="simple_tag">Boxed layout</span>
                    <span class="simple_tag">Scroll footer</span>
                    <span class="simple_tag">Fixed footer</span>
                    <div class="m-t-md">
                        <p>Check the Dashboard v.4 with top navigation layout</p>
                        <div class="p-lg ">
                        <a href="dashboard_4.html"><img class="img-fluid img-shadow" src="img/dashboard4_2.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-lg-6">
            <div class="ibox ">
                <div class="ibox-content text-center p-md">

                    <h4 class="m-b-xxs">Basic left side navigation layout </h4><small>(main layout)</small>
                    <p>Available configure options</p>
                    <span class="simple_tag">Collapse menu</span>
                    <span class="simple_tag">Fixed sidebar</span>
                    <span class="simple_tag">Scroll navbar</span>
                    <span class="simple_tag">Top fixed navbar</span>
                    <span class="simple_tag">Boxed layout</span>
                    <span class="simple_tag">Scroll footer</span>
                    <span class="simple_tag">Fixed footer</span>
                    <div class="m-t-md">
                        <p>Check the Dashboard v.4 with basic layout</p>
                        <div class="p-lg">
                            <a href="dashboard_4_1.html"><img class="img-fluid img-shadow" src="img/dashboard4_1.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
  

 </div> -->

<div id="selectBox">
<select name="limit" id="limit" onchange="limitSelect(this.value)">
     <option value="1">10개</option>
     <option value="25">25개</option>
     <option value="50">50개</option>   
</select>
</div>


<div id="grid"></div>



<script> 




	var dataSource = {
			  withCredentials: false,  
			  initialRequest: true,
			  api: {
			      readData: { url: '/admin/ChangeLimit.do', method: 'GET' },
			      createData: { url: '/api/create', method: 'POST' },
			      updateData: { url: '/api/update', method: 'PUT' },
			      deleteData: { url: '/api/delete', method: 'DELETE' },
			      modifyData: { url: '/api/modify', method: 'POST' }
			  }
			}


	var jsonData = ${adminJson};

	const grid = new tui.Grid({
	    el: document.getElementById('grid'),
	    data: {
	    	  api: {
			      readData: { url: '/admin/ChangeLimit.do', method: 'GET' }	 
	    	  } 
	    },
	    scrollX: false,
		scrollY: false,
	    pageOptions: { 
	    	 perPage: 5
	    },
	    	 
	    	 
	    columns: [
	    	{
	    	    header: 'id',
	    	    name: 'USER_ID'
	    	  },
	    	  {
	    	    header: 'email',
	    	    name: 'EMAIL'
	    	  //,  editor: 'text'
	    	  }
	    ],
	 
	    
	  });
	
	
	 // var callbackData = ${resultJson};
	  //grid.resetData(jsonData);
	

	

function limitSelect(value) {
	
	var limitSelect = {
		limit : value		
	}
	
	/* grid.use('Net',{
	    	api : {
	    	'readData' : '/admin/ChangeLimit.do',
	    	},
	    	readDataMethod: 'POST'

	});

	 */
		$.ajax({
		
		type : "POST",
		url : "/admin/ChangeLimit.do",
		dataType : "json",
		data : limitSelect,
		success : function(result) {
		
	
			grid.resetData(result.data.contents);
				
		},
		error : function() {
			alert("오류가 발생하였습니다.");
		}
		
	}); 
	
	
}


</script>
    
    
    
    
    
    
    
    

