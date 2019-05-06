function sendSection(flag) {
	document.sects.section.value = flag;
	document.sects.submit();
}


function sendEditFunction(flag) {
	document.formEdit.paramEdit.value = flag;
	document.formEdit.submit();
}



function sendToEditJsp(id, contentTask) {
	var tmp_contTask = document.createElement("input");
	tmp_contTask.type = 'hidden';
	tmp_contTask.name = 'contTask';
	tmp_contTask.value = contTask;
	
	var tmp_idTask = document.createElement("input");
	tmp_idTask.type = 'hidden';
	tmp_idTask.name = 'id';
	tmp_idTask.value = idTask;
	
	var tmp_form = document.createElement("form");
	tmp_form.action = '/Web2/addTask.jsp';
	tmp_form.method = 'post';
	tmp_form.name = 'toJspForm';
	
	tmp_form.appendChild(tmp_contTask);
	tmp_form.appendChild(tmp_idTask);
	
	document.body.appendChild(tmp_form);
	
	tmp_form.submit();
}

