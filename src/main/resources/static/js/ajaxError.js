$.ajax({
    url:"/Grh/err/getAjax",
    type: "POST",
    async: false,
    success: function(data) {
        if (data.status == "200" && data.msg == "OK") {
            alert("success");
        } else {
            alert(data.msg);
        }
    },
    error: function(response, ajaxOptions, thrownError) {
        alert("error");
    }
});