</main><!-- End #main -->
<footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>Mr.bean</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
    <!-- Chatbot Button -->
    <div id="chatbot-button" class="chatbot-button">
        <i class="bi bi-chat-dots"></i>
    </div>

    <!-- Chatbot Window -->
    <div id="chatbot-window" class="chatbot-window">
        <div class="chatbot-header">
            <h5>Chatbot</h5>
            <button id="close-chatbot" class="close-chatbot">&times;</button>
        </div>
        <div class="chatbot-body">
            <div id="chatbot-messages" class="chatbot-messages"></div>

            <!-- Spinner for loading -->
            <div id="chatbot-spinner" class="spinner-border text-dark" role="status" style="display: none;">
                <span class="visually-hidden">Loading...</span>
            </div>

            <input type="text" id="chatbot-input" class="chatbot-input" placeholder="Type a message...">
            <button id="send-chatbot" class="send-chatbot">Send</button>
        </div>
    </div>
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