$(document).ready(function() {
    $("#edit").click(function () {
        $('#studentGender').attr("disabled",false);
        $('#dateOfbirth').attr("disabled",false);
        $('#studentPhoneNumber').attr("disabled",false);
        $("#submit").css('display','block');
        $("#cancel").css('display','block');
        $("#edit").css('display','none');
    });
    $("#cancel").click(function () {
        $('#studentGender').attr("disabled",true);
        $('#dateOfbirth').attr("disabled",true);
        $('#studentPhoneNumber').attr("disabled",true);
        $("#submit").css('display','none');
        $("#cancel").css('display','none');
        $("#edit").css('display','block');
    })
});

//添加是否成功弹窗
window.onload = function () {
    let msg = document.getElementById("msg").value;
    if (msg　!== ""){
        layer.alert(msg);
    }
}

function selectRole(){
    layer.open({
        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        type:1,
        title:"请假",
        area: ['50%','50%'],
        content:$("#newLeave").html()
    });
}