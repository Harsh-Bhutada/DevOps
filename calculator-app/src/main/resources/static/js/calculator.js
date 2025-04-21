function calculate() {
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    const operation = document.getElementById('operation').value;
    
    if (isNaN(num1) || isNaN(num2)) {
        document.getElementById('result').innerText = 'Please enter valid numbers';
        return;
    }

    const calculation = {
        num1: num1,
        num2: num2,
        operation: operation
    };

    fetch('/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(calculation)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('display').innerText = data.result;
        document.getElementById('result').innerText = `${data.num1} ${data.operation} ${data.num2} = ${data.result}`;
    })
    .catch(error => {
        document.getElementById('result').innerText = 'Error in calculation';
        console.error('Error:', error);
    });
}