function fetchTrucks() {
    const truckType = document.getElementById('truckType').value.trim();
    if (truckType === '') {
        alert('Please enter a truck type.');
        return;
    }

    console.log('Fetching trucks for type:', truckType);

    fetch(`http://localhost:8080/api/trucks/type?type=${encodeURIComponent(truckType)}`)
        .then(response => {
            console.log('Response status:', response.status);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(trucks => {
            console.log('Fetched trucks:', trucks);
            displayTrucks(trucks);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error fetching trucks. Please try again.');
        });
}



function displayTrucks(trucks) {
    const truckList = document.getElementById('truckList');
    truckList.innerHTML = ''; // Clear the list for new results

    if (trucks.length === 0) {
        const noTrucksMessage = document.getElementById('noTrucksMessage');
        noTrucksMessage.classList.remove('d-none');
        noTrucksMessage.textContent = 'No trucks found for the specified type.';
        return;
    } else {
        document.getElementById('noTrucksMessage').classList.add('d-none');
    }

    // Loop through each truck and add list items to the UL
    trucks.forEach(truck => {
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.textContent = `${truck.applicant} - Truck`;
        truckList.appendChild(li);
    });
}