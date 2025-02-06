function openBomInfoModal(bomId) {
            // Remove the "BOM" prefix from the bomId
            const id = bomId.replace('BOM', '');

            // Fetch BOM information via AJAX
            $.ajax({
                url: `/api/billofmaterials/${id}`,  // Adjusted URL to match the endpoint
                method: 'GET',
                success: function(response) {
                    if (response) {
                        document.getElementById("bomName").value = response.bomName || '';
                        document.getElementById("rmCode").value = response.rmCode || '';
                        document.getElementById("bomRatio").value = response.bomRatio || '';
                        document.getElementById("bomDescription").value = response.bomDescription || '';
                        $('#editBomModal').modal('show'); // Show the modal
                    } else {
                        alert("Failed to retrieve BOM information.");
                    }
                },
                error: function() {
                    alert("Error occurred while fetching BOM information.");
                }
            });
        }

        function openEditModal(pCode, pName, pDescription, bomId) {
            // Populate the modal with product information
            document.getElementById("pCode").value = pCode;
            document.getElementById("pName").value = pName;
            document.getElementById("pDescription").value = pDescription;
            document.getElementById("bomId").value = bomId;
        }