$(function(){
    $("#submit").click(function () {
        let radiovalue = getRadioValue("choice");
        let pageContext = document.getElementById("PageContext").value;
        $.ajax({
            url:pageContext+"/logon",
            type:"post",
            dateType:"json",
            contentType:"application/x-www-form-urlencoded",
            data:$("form").serialize(),
            success:function (data) {
                if (data.login === "true"){

                    if (radiovalue == "admin"){
                        $(location).prop("href",pageContext+"/hncstTransactionManagement/adminIndex");
                    } else if(radiovalue == "transaction"){
                        $(location).prop("href",pageContext+"/hncstTransactionManagementTransaction/transactionIndex");
                    } else if(radiovalue == "student"){
                        $(location).prop("href",pageContext+"/hncstTransactionManagementStudent/studentIndex");

                    } else {
                        layer.alert("出现了一些意料之外的问题！");
                    }

                } else{
                    layer.alert(data.msg);
                }
            }
        });
    });
})


function getRadioValue(radioName){
    var radioValue;
    radioValue=document.getElementsByName(radioName);
    if(radioValue!=null){
        var i;
        for(i=0;i<radioValue.length;i++){
            if(radioValue[i].checked){
                return radioValue[i].value;
            }
        }
    }
    return null;
}
