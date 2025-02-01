function init(){
    var url = location.href;
    var confmKey = "TESTJUSOGOKR";
    var resultType = "3";
    var inputYn= "<%=inputYn%>";
    if(inputYn != "Y"){
        document.form.confmKey.value = confmKey;
        document.form.returnUrl.value = url;
        document.form.resultType.value = resultType;
        document.form.action="https://business.juso.go.kr/addrlink/addrLinkUrl.do";
        document.form.submit();
    } else {
        fetch(`/api/jusoCallback?roadFullAddr=<%= roadFullAddr %>&addrDetail=<%= addrDetail %>&zipNo=<%= zipNo %>`)
            .then(response => response.json())
            .then(data => {
                opener.jusoCallBack(data.roadFullAddr, data.addrDetail, data.zipNo);
                window.close();
            });
    }
}

<script language="javascript">
function init(){
    var url = location.href;
    var confmKey = "TESTJUSOGOKR";
    var resultType = "3";
    var inputYn= "<%=inputYn%>";
    if(inputYn != "Y"){
        document.form.confmKey.value = confmKey;
        document.form.returnUrl.value = url;
        document.form.resultType.value = resultType;
        document.form.action="https://business.juso.go.kr/addrlink/addrLinkUrl.do";
        document.form.submit();
    } else {
        fetch(`/api/jusoCallback?roadFullAddr=<%= roadFullAddr %>&addrDetail=<%= addrDetail %>&zipNo=<%= zipNo %>`)
            .then(response => response.json())
            .then(data => {
                opener.jusoCallBack(data.roadFullAddr, data.addrDetail, data.zipNo);
                window.close();
            });
    }
}
</script>