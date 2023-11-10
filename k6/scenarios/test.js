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
        deadline: new Date().toISOString(),
        isCompleted: false
    };

    let response = http.post('http://localhost:8080/task/createTask', JSON.stringify(data), {
        headers: { 'Content-Type': 'application/json' },
    });

    data = JSON.parse(response.body);
    let dataId = data.id.toString();

    response = http.get('http://localhost:8080/task/getTaskById?id=' + dataId);

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
    
    response = http.put('http://localhost:8080/task/completedTaskById?id=' + dataId);

    response = http.put('http://localhost:8080/task/uncompletedTaskById?id=' + dataId);

    response = http.post('http://localhost:8080/task/changeTaskTitleById', JSON.stringify({ id: data.id, title: "Teste2" }), {
        headers: { 'Content-Type': 'application/json' },
    });
    
    response = http.post('http://localhost:8080/task/changeTaskDescriptionById', JSON.stringify({ id: data.id, description: "Teste2" }), {
        headers: { 'Content-Type': 'application/json' },
    });
    
    response = http.post('http://localhost:8080/task/changeTaskDeadlineById', JSON.stringify({ id: data.id, deadline: new Date().toISOString() }), {
        headers: { 'Content-Type': 'application/json' },
    });

    response = http.del('http://localhost:8080/task/deleteTask?id=' + dataId);

    sleep(1);
}