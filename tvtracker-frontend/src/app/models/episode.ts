import { Show } from './show';

export interface Episode {
  id: number;
  show: Show;
  seasonNumber: number;
  episodeNumber: number;
  imgLink: string;
  name: string;
  airDate: string;
  createdAt: string;
}