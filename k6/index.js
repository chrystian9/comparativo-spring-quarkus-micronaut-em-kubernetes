import teste from "./scenarios/test.js";
import { group, sleep } from "k6";

export default () => {
    group('ToDoListSpring.Api', () =>{
        teste();
    });

    sleep(1);
}