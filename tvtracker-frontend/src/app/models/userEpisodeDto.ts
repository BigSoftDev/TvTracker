import { Episode } from "./episode";

export interface UserEpisodeDto {
    episode: Episode;
    isWatched: boolean;
}