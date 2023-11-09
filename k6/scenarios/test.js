import http from 'k6/http';
import { sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';
import { check, fail } from 'k6';

export let GetTaskDuration = new Trend('get_customer_duration');
export let GetTaskFailRate = new Rate('get_customer_fail_rate');
export let GetTaskSuccessRate = new Rate('get_customer_success_rate');
export let GetTaskReqs = new Rate('get_customer_reqs');

export default function () {
    let data = {
        title: "Teste1",
        description: "Teste1",
        deadline: formatarData(new Date()),
        isCompleted: false
    };

    let response = http.post('http://localhost:8080/task/createTask', JSON.stringify(data), {
        headers: { 'Content-Type': 'application/json' },
    });

    data = JSON.parse(response.body);

    response = http.get('http://localhost:8080/task/getTaskById?id=' + data.id);

    GetTaskDuration.add(response.timings.duration);
    GetTaskReqs.add(1);
    GetTaskFailRate.add(response.status == 0 || response.status > 399);
    GetTaskSuccessRate.add(response.status < 399);

    let durationMsg = 'Max Duration ${1000/1000}s';

    if(!check(response, {
        'max duration': (r) => r.timings.duration < 1000,
    })){
        fail(durationMsg);
    };
    
    response = http.put('http://localhost:8080/task/completedTaskById?id=' + data.id);

    response = http.put('http://localhost:8080/task/uncompletedTaskById?id=' + data.id);

    response = http.post('http://localhost:8080/task/changeTaskTitleById', JSON.stringify({ id: data.id, title: "Teste2" }), {
        headers: { 'Content-Type': 'application/json' },
    });
    
    response = http.post('http://localhost:8080/task/changeTaskDescriptionById', JSON.stringify({ id: data.id, description: "Teste2" }), {
        headers: { 'Content-Type': 'application/json' },
    });
    
    response = http.post('http://localhost:8080/task/changeTaskDeadlineById', JSON.stringify({ id: data.id, deadline: formatarData(new Date()) }), {
        headers: { 'Content-Type': 'application/json' },
    });

    response = http.del('http://localhost:8080/task/deleteTask?id=' + data.id);

    sleep(1);
}

function formatarData(data) {
    const ano = data.getFullYear();
    const mes = String(data.getMonth() + 1).padStart(2, '0');
    const dia = String(data.getDate()).padStart(2, '0');
    const horas = String(data.getHours()).padStart(2, '0');
    const minutos = String(data.getMinutes()).padStart(2, '0');
  
    return `${ano}-${mes}-${dia} ${horas}:${minutos}`;
  }