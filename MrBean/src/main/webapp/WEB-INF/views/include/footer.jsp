</main>
<!-- End #main -->
<footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>Mr.bean</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
    <!-- Databot Button -->
    <c:if test="${not empty sessionScope.loggedInUser}">
    <div id="databot-button" class="databot-button">
        <i class="bi bi-chat-dots"></i>
    </div>

    <!-- Databot Window -->
    <div id="databot-window" class="databot-window" style="display: none;">
        <div class="databot-header">
            <h5>Data bot</h5>
            <button id="close-databot" class="close-databot">&times;</button>
        </div>
        <div class="databot-body">
            <div id="databot-messages" class="databot-messages"></div>

            <!-- Spinner for loading -->
            <div id="databot-spinner" class="spinner-border text-dark" role="status" style="display: none;">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div id="databot-response" class="databot-response">
                <input type="text" id="databot-input" class="databot-input" placeholder="Type a message...">
                <button id="send-databot" class="send-databot"><i class="bi bi-arrow-return-left"></i></button>
            </div>
            <!-- Resize handle -->
            <div id="databot-resize-handle"></div>
        </div>
    </div>
    </c:if>
</footer>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="${pageContext.request.contextPath}/resources/assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/chart.js/chart.umd.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/echarts/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/quill/quill.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/tinymce/tinymce.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>

</body>
</html>