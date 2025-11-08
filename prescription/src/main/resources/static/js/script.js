// Form Validation
document.addEventListener('DOMContentLoaded', function() {

    // Prescription Form Validation
    const prescriptionForm = document.getElementById('prescriptionForm');

    if (prescriptionForm) {
        prescriptionForm.addEventListener('submit', function(e) {
            let isValid = true;

            // Validate Patient Name
            const patientName = document.getElementById('patientName');
            if (patientName && patientName.value.trim() === '') {
                showError(patientName, 'Patient name is required');
                isValid = false;
            } else if (patientName) {
                clearError(patientName);
            }

            // Validate Patient Age
            const patientAge = document.getElementById('patientAge');
            if (patientAge) {
                const age = parseInt(patientAge.value);
                if (isNaN(age) || age < 0 || age > 150) {
                    showError(patientAge, 'Please enter a valid age between 0 and 150');
                    isValid = false;
                } else {
                    clearError(patientAge);
                }
            }

            // Validate Gender
            const patientGender = document.getElementById('patientGender');
            if (patientGender && patientGender.value === '') {
                showError(patientGender, 'Please select a gender');
                isValid = false;
            } else if (patientGender) {
                clearError(patientGender);
            }

            // Validate Prescription Date
            const prescriptionDate = document.getElementById('prescriptionDate');
            if (prescriptionDate && prescriptionDate.value === '') {
                showError(prescriptionDate, 'Prescription date is required');
                isValid = false;
            } else if (prescriptionDate) {
                clearError(prescriptionDate);
            }

            // Validate Next Visit Date (if provided)
            const nextVisitDate = document.getElementById('nextVisitDate');
            if (nextVisitDate && nextVisitDate.value && prescriptionDate && prescriptionDate.value) {
                const prescDate = new Date(prescriptionDate.value);
                const visitDate = new Date(nextVisitDate.value);

                if (visitDate <= prescDate) {
                    showError(nextVisitDate, 'Next visit date must be after prescription date');
                    isValid = false;
                } else {
                    clearError(nextVisitDate);
                }
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    }

    // Load Drug Interactions
    const loadDrugInfoBtn = document.getElementById('loadDrugInfo');
    if (loadDrugInfoBtn) {
        loadDrugInfoBtn.addEventListener('click', async function() {
            const container = document.getElementById('drugInfoContainer');
            const button = this;

            // Disable button and show loading state
            button.disabled = true;
            button.textContent = 'Loading...';
            container.innerHTML = '<p style="text-align: center; padding: 20px;">Fetching drug interaction data from RxNav API...</p>';

            try {
                const response = await fetch('/api/v1/drug-interactions');

                if (!response.ok) {
                    throw new Error('Failed to fetch data from API');
                }

                const data = await response.json();

                // Format and display the data
                if (data && data.interactionTypeGroup && data.interactionTypeGroup.length > 0) {
                    let html = '<div style="margin-bottom: 15px;"><strong>Drug Interaction Results:</strong></div>';

                    data.interactionTypeGroup.forEach(function(group) {
                        if (group.interactionType && group.interactionType.length > 0) {
                            group.interactionType.forEach(function(type) {
                                if (type.interactionPair && type.interactionPair.length > 0) {
                                    type.interactionPair.forEach(function(pair) {
                                        html += '<div class="interaction-item">';
                                        html += '<h4>Interaction Found</h4>';

                                        if (pair.interactionConcept && pair.interactionConcept.length >= 2) {
                                            html += '<p><strong>Drugs:</strong> ' +
                                                   pair.interactionConcept[0].minConceptItem.name +
                                                   ' + ' +
                                                   pair.interactionConcept[1].minConceptItem.name +
                                                   '</p>';
                                        }

                                        if (pair.severity) {
                                            html += '<p><strong>Severity:</strong> <span style="color: ' +
                                                   (pair.severity === 'high' ? '#dc3545' : '#ffc107') +
                                                   '">' + pair.severity.toUpperCase() + '</span></p>';
                                        }

                                        if (pair.description) {
                                            html += '<p><strong>Description:</strong> ' + pair.description + '</p>';
                                        }

                                        html += '</div>';
                                    });
                                }
                            });
                        }
                    });

                    container.innerHTML = html;
                } else {
                    container.innerHTML = '<div class="interaction-item"><p>No drug interactions found or data structure is different.</p><details><summary>View Raw JSON</summary><pre>' +
                                        JSON.stringify(data, null, 2) + '</pre></details></div>';
                }

                button.textContent = 'Refresh Drug Interactions';
                button.disabled = false;

            } catch (error) {
                container.innerHTML = '<div class="error"><strong>Error:</strong> ' + error.message +
                                    '<br><small>Please check if the external API is accessible.</small></div>';
                button.textContent = 'Retry Loading';
                button.disabled = false;
            }
        });
    }
});

// Helper Functions
function showError(element, message) {
    element.classList.add('is-invalid');
    let errorSpan = element.nextElementSibling;

    if (!errorSpan || !errorSpan.classList.contains('error')) {
        errorSpan = document.createElement('span');
        errorSpan.classList.add('error');
        element.parentNode.insertBefore(errorSpan, element.nextSibling);
    }

    errorSpan.textContent = message;
}

function clearError(element) {
    element.classList.remove('is-invalid');
    const errorSpan = element.nextElementSibling;

    if (errorSpan && errorSpan.classList.contains('error')) {
        errorSpan.remove();
    }
}

// Confirmation for Delete Actions
document.querySelectorAll('a[href*="/delete/"]').forEach(function(link) {
    link.addEventListener('click', function(e) {
        if (!confirm('Are you sure you want to delete this prescription?')) {
            e.preventDefault();
        }
    });
});