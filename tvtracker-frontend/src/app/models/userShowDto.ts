import { Show } from "./show";
import { ShowStatus } from "./showStatus";

export interface UserShowDto {  
    show: Show;
    userShowId: number;
    favorite: boolean;
    rating: number;
    status: ShowStatus;
}