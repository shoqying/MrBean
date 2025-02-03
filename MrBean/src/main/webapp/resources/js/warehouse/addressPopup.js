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

function openAddressPopup() {
    const url = '/popup/jusoPopup?inputYn=N'; // Ensure the URL is correct
    document.getElementById('addressSearchFrame').src = url;
    $('#addressSearchModal').modal('show');
}

function jusoCallBack(roadFullAddr, addrDetail, zipNo) {
    document.getElementById('wRoadFullAddr').value = roadFullAddr;
    document.getElementById('wAddrDetail').value = addrDetail;
    document.getElementById('wZipNo').value = zipNo;
    $('#addressSearchModal').modal('hide');
}