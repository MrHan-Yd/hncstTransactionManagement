function selectRole(){
    layer.open({
        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        type:1,
        title:"新增教师",
        area: ['50%','50%'],
        content:$("#newTransaction").html()
    });
}

function studentSelectRole(){
    layer.open({
        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        type:1,
        title:"新增学生",
        area: ['50%','50%'],
        content:$("#newStudent").html()
    });
}
//添加是否成功弹窗
window.onload = function () {
    let msg = document.getElementById("msg").value;
    if (msg　!== ""){
        layer.alert(msg);
    }
}