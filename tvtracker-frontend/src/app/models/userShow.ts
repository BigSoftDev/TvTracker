import { ShowStatus } from "./showStatus";

export interface UserShow {
    id: number;
    userId: number;
    showId: number;
    status: ShowStatus;
    rating: number;
    favorite: boolean;
}