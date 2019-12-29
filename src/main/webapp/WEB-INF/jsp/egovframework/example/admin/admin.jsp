<%@ page language="java" contentType="text/html; charset=UTF-8" %>  


<link rel="stylesheet" href="https://uicdn.toast.com/tui-grid/latest/tui-grid.css" />
<script src="https://uicdn.toast.com/tui-grid/latest/tui-grid.js"></script>


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

<div id="grid"></div>

 <script> 


var gridData = [
  {
    id: '10012',
    city: 'Seoul',
    country: 'South Korea'
  },
  {
    id: '10013',
    city: 'Tokyo',
    country: 'Japan'    
  },
  {
    id: '10014',
    city: 'London',
    country: 'England'
  },
  {
    id: '10015',
    city: 'Ljubljana',
    country: 'Slovenia'
  },
  {
    id: '10016',
    city: 'Reykjavik',
    country: 'Iceland'
  }
];


var jsonData = ${adminJson};

const grid = new tui.Grid({
    el: document.getElementById('grid'),
     data: jsonData,
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
    ]
  });

 
	grid.resetData(jsonData);

</script>
    
    
    
    
    
    
    
    

