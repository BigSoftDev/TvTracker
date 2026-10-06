import { UserEpisodeDto } from "./userEpisodeDto";
export interface EpisodeAction {
  label: string;
  visible?: (episode: UserEpisodeDto) => boolean;
  execute: (episode: UserEpisodeDto     ) => void;
}