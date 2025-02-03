<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.datatable-top > nav:first-child, .datatable-top > div:first-child, .datatable-bottom > nav:first-child, .datatable-bottom > div:first-child {
    float: right;
}
</style>
<c:set var="pageTitle" value="창고 등록"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">
                <i class="bi bi-pencil-square me-1"></i>창고 등록
            </h5>
            <!-- 창고 등록 폼 -->
            <form id="warehouseForm" class="row g-3" onsubmit="submitForm(event)">
                <!-- 창고 코드 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wCode"
                            name="wCode"
                            placeholder="예: A1"
                            required
                            autocomplete="off"
                            oninput="validateInput('wCode')"
                            title="창고 코드를 입력하세요."
                        />
                        <label for="wCode">창고 코드</label>
                    </div>
                    <small id="wCodeError" class="form-text text-danger" style="display: none;">
                        창고 코드는 A1~Z99 형식으로 입력해주세요. (예: A1, B99)
                    </small>
                </div>

                <!-- 창고 이름 -->
                <div class="col-md-7">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wCode"
                            name="wCode"
                            placeholder="예: A1"
                            required
                            autocomplete="off"
                            autofocus
                            oninput="validateInput('wCode')"
                            title="창고 코드를 입력하세요."
                        />
                        <label for="wCode">창고 코드</label>
                    </div>
                    <small id="wCodeError" class="form-text text-danger" style="display: none;">
                        창고 코드는 A1~Z99 형식으로 입력해주세요. (예: A1, B99)
                    </small>
                </div>

                <!-- 창고 이름 -->
                <div class="col-md-7">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wName"
                            name="wName"
                            placeholder="예: 중앙 창고"
                            required
                            autocomplete="off"
                            oninput="validateInput('wName')"
                            title="창고 이름을 입력하세요."
                        />
                        <label for="wName">창고 이름</label>
                    </div>
                    <small id="wNameError" class="form-text text-danger" style="display: none;">
                        창고 이름을 입력해주세요.
                    </small>
                </div>

                <!-- 전체 주소 -->
                <div class="col-sm-7">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wRoadFullAddr"
                            name="wRoadFullAddr"
                            placeholder="예: 서울특별시 강남구 테헤란로 123"
                            required
                            readonly
                            autocomplete="off"
                            oninput="validateInput('wRoadFullAddr')"
                            title="전체 주소를 입력하세요."
                        />
                        <label for="wRoadFullAddr">전체 주소</label>
                    </div>
                    <small id="wRoadFullAddrError" class="form-text text-danger" style="display: none;">
                        도로명 주소를 입력해주세요.
                    </small>
                </div>

                <!-- 상세주소 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wAddrDetail"
                            name="wAddrDetail"
                            placeholder="예: 101호"
                            required
                            autocomplete="off"
                            oninput="validateInput('wAddrDetail')"
                            title="상세 주소를 입력하세요."
                        />
                        <label for="wAddrDetail">상세주소</label>
                    </div>
                    <small id="wAddrDetailError" class="form-text text-danger" style="display: none;">
                        상세주소를 입력해주세요.
                    </small>
                </div>

                <!-- 우편번호 -->
                <div class="col-sm-7">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="wZipNo"
                            name="wZipNo"
                            placeholder="예: 12345"
                            required
                            readonly
                            autocomplete="off"
                            oninput="validateInput('wZipNo')"
                            title="우편번호를 입력하세요."
                        />
                        <label for="wZipNo">우편번호</label>
                    </div>
                    <small id="wZipNoError" class="form-text text-danger" style="display: none;">
                        우편번호를 입력해주세요.
                    </small>
                </div>
                <!-- 창고 설명 -->
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <textarea
                            class="form-control"
                            id="wDescription"
                            name="wDescription"
                            placeholder="창고에 대한 상세 설명(최대 500자)"
                            rows="4"
                            autocomplete="off"
                            style="height: 120px; resize: none; overflow-y: auto;"
                            oninput="updateCharacterCount()"
                            title="창고에 대해 자세한 정보를 적어주세요."
                        ></textarea>
                        <label for="wDescription">창고 설명</label>
                    </div>
                    <small id="charCount" class="text-muted" style="float: right;">0/500</small>
                </div>

                <!-- 제출 및 초기화 버튼 -->
                <div class="col-12 text-center">
                    <button id="submitBtn" type="submit" class="btn btn-success me-2" disabled title="필수 입력란을 모두 채운 뒤 등록을 진행하세요.">
                        <b><i class="bi bi-check-circle"></i> 등록</b>
                    </button>
                    <button type="reset" class="btn btn-secondary" title="입력란을 모두 초기화합니다.">
                        <b><i class="bi bi-arrow-counterclockwise me-1"></i> 초기화</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Address Search Modal -->
<div class="modal fade" id="addressSearchModal" tabindex="-1" role="dialog" aria-labelledby="addressSearchModalLabel" style="display: block;" inert>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addressSearchModalLabel">주소 검색</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="postcodeContainer" style="width:100%; height:450px;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function openAddressPopup() {
    var modal = document.getElementById('addressSearchModal');
    modal.removeAttribute('inert');
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.roadAddress; // Always use road address
            var extraAddr = '';

            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            if (extraAddr !== '') {
                extraAddr = ' (' + extraAddr + ')';
            }

            document.getElementById('sample2_extraAddress').value = extraAddr;
            document.getElementById('wZipNo').value = data.zonecode;
            document.getElementById('wRoadFullAddr').value = addr;
            document.getElementById('wAddrDetail').focus();

            $('#addressSearchModal').modal('hide');
            modal.setAttribute('inert', '');
        },
        width: '100%',
        height: '100%'
    }).embed(document.getElementById('postcodeContainer'));

    $('#addressSearchModal').modal('show');
}

document.getElementById('wRoadFullAddr').addEventListener('click', openAddressPopup);
document.getElementById('wZipNo').addEventListener('click', openAddressPopup);
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

                <!-- 제출 및 초기화 버튼 -->
                <div class="col-12 text-center">
                    <button id="submitBtn" type="submit" class="btn btn-success me-2" disabled title="필수 입력란을 모두 채운 뒤 등록을 진행하세요.">
                        <b><i class="bi bi-check-circle"></i> 등록</b>
                    </button>
                    <button type="reset" class="btn btn-secondary" title="입력란을 모두 초기화합니다.">
                        <b><i class="bi bi-arrow-counterclockwise me-1"></i> 초기화</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Address Search Modal -->
<div class="modal fade" id="addressSearchModal" tabindex="-1" role="dialog" aria-labelledby="addressSearchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addressSearchModalLabel">주소 검색</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <iframe id="addressSearchFrame" src="" width="100%" height="450px" frameborder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/warehouse/addressPopup.js'/>"></script>
<script src="<c:url value='/resources/js/warehouse/validation.js'/>"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
