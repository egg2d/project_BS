<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<script src="/bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.js"></script>
<script type="text/javascript"
	src="https://uicdn.toast.com/tui.pagination/latest/tui-pagination.js"></script>
<script src="/toast/tui-grid.js"></script> 
 <link rel="stylesheet" href="https://uicdn.toast.com/tui-grid/latest/tui-grid.css" />
 <link rel="stylesheet" href="https://uicdn.toast.com/tui.pagination/latest/tui-pagination.css" />
 

<style>
#selectBox {
	margin-bottom: 5px
}
</style>


<div id="selectBox">
	<select name="limit" id="limit" onchange="limitSelect(this.value)">
		<option value="1">10개</option>
		<option value="25">25개</option>
		<option value="50">50개</option>
	</select>
</div>

<button id="sync" onclick="syncServer()">업데이트22</button>
	
<div id="grid"></div>

<script> 

// 커스텀 에디터 class
class CustomTextEditor {
	constructor(props) {
		const el = document.createElement('input');
		const { maxLength } = props.columnInfo.editor.options;

		el.type = 'text';
		el.maxLength = maxLength;
		el.value = String(props.value);

		this.el = el;
	}

	getElement() {
		return this.el;
	}

	getValue() {
		return this.el.value;
	}

	mounted() {
		this.el.select();
	}
}


</script>

<script> 


	// toast DataSource CRUD
	var dataSource = {
			  withCredentials: false,  
			  initialRequest: true,
			  api: {
			      readData: { url: '/admin/ChangeLimit.do', method: 'GET' },
			      createData: { url: '/api/create', method: 'POST' },
			      updateData: { url: '/admin/updateAdminDate.do', method: 'POST' },
			  }
	}


	//var jsonData = ${adminJson};

	const grid = new tui.Grid({
	    el: document.getElementById('grid'),
	    data:dataSource,
	    rowHeaders: ['checkbox'],
	    scrollX: false,
		scrollY: false,
	    pageOptions: { 
	    	 perPage: 3
	    },
	    	  
	    columns: [
	    	
	    	  {
		    	    header: 'UserKeyValue',
		    	    name: 'USER_ID_NO',		    	
		      },
	    	  {
	    	    header: '아이디',
	    	    name: 'USER_ID',
	            filter: { type: 'text', showApplyBtn: true, showClearBtn: true },
	    		onBeforeChange(ev){
					console.log('Before change:' + ev);
				},
				onAfterChange(ev){
					console.log('After change:' + ev);
				},
				editor: {
					type: CustomTextEditor,
					options: {
						maxLength: 10
					}
				}
	    	  },
	    	  {
		    	    header: '비밀번호',
		    	    name: 'PASSWORD',
		    		onBeforeChange(ev){
						console.log('Before change:' + ev);
					},
					onAfterChange(ev){
						console.log('After change:' + ev);
					},
					editor: {
						type: CustomTextEditor,
						options: {
							maxLength: 25
						}
					}
		      },
	    	  {
	    	    header: '이메일',
	    	    name: 'EMAIL',
	    		onBeforeChange(ev){
					console.log('Before change:' + ev);
				},
				onAfterChange(ev){
					console.log('After change:' + ev);
				},
				editor: {
					type: CustomTextEditor,
					options: {
						maxLength: 100
					}
				}
	    	  },
	    	  {
		    	    header: '회원탈퇴여부',
		    	    name: 'DEL_YN',
		    	    validation : {
					       regExp: /^[YN]$/
						},	
		    		onBeforeChange(ev){
						console.log('Before change:' + ev);
					},
					onAfterChange(ev){
						console.log('After change:' + ev);
					},
					editor: {
						type: CustomTextEditor,
						options: {
							maxLength: 1
						}
					
					}
		       },
		       {
		    	    header: '유저레벨',
		    	    name: 'USER_LVL',
		    	    validation : {
					       regExp: /^[0-1]$/
					},	
		    		onBeforeChange(ev){
						console.log('Before change:' + ev);
					},
					onAfterChange(ev){
						console.log('After change:' + ev);
					},
					editor: {
						type: CustomTextEditor,
						options: {
							maxLength: 10
						}
					}
		       }
	    ],
	  
	    
	  });
	
	
	grid.on('response', ev => {
		 
		const {response} = ev.xhr;
		 const responseObj = JSON.parse(response);

		 if(responseObj.message == "계정 업데이트를 성공하였습니다.") {
			alert(responseObj.message); 
			window.location.reload();
		 } 
		 
		 

		 console.log('result : ', responseObj.result);
		 console.log('data : ', responseObj.data); 
	});

	
function limitSelect(value) {
	
	var limitSelect = {
		limit : value		
	}

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

function syncServer() {
	  
	 const { rowKey, columnName } = grid.getFocusedCell();

	  if (rowKey && columnName) {
	    grid.finishEditing(rowKey, columnName);
	  }

	  grid.request('updateData', {
	    checkedOnly: true
	  });
	
}

</script>









