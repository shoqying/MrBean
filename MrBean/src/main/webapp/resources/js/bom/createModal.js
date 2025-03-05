$(document).ready(function() {
    $('#rawMaterialModal').on('show.bs.modal', function (e) {
        loadRawMaterialList();
        $('#rawMaterialModal').removeAttr('inert'); // Remove inert attribute when modal is shown
    });

    $('#rawMaterialModal').on('hide.bs.modal', function (e) {
        $('#rawMaterialModal').attr('inert', ''); // Add inert attribute when modal is hidden
    });
});

function loadRawMaterialList() {
    $.ajax({
        url: '/api/rawmaterials',
        type: 'GET',
        success: function(data) {
            let tbody = $('#rawMaterialTableBody');
            tbody.empty(); // Clear existing rows

            data.forEach(function(item) {
                let row = `
                    <tr>
                        <td>${item.rmCode}</td>
                        <td>${item.rmName}</td>
                        <td>${item.rmOrigin}</td>
                        <td>${item.rmStorageMethod}</td>
                        <td>
                            <button type="button" class="btn btn-primary"
                                onclick="selectRawMaterial('${item.rmCode}', '${item.rmName}')">
                                선택
                            </button>
                        </td>
                    </tr>
                `;
                tbody.append(row);
            });
        },
        error: function(err) {
            console.error(err);
        }
    });
}

function selectRawMaterial(code, name) {
    $('#rmCode').val(code);
    $('#rmName').val(name);
    $('#rawMaterialModal').removeClass('show').css('display', 'none').attr('aria-hidden', 'true');
    $('.modal-backdrop').remove();
}