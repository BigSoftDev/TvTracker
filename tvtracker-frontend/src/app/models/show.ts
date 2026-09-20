import { ShowStatus } from "./showStatus";

export interface Show {
  id: number;
  name: string;
  slug: string;
  imgLink: string | null;
  nameTranslations: string[] | null;
  overviewTranslations: string[] | null;
  aliases: any[] | null; // Using 'any' as the type is not specified. Use a more specific type if known.
  firstAired: string;
  lastAired: string;
  nextAired: string;
  score: number;
  status: ShowStatus; // Use the nested interface here
  originalCountry: string;
  originalLanguage: string;
  defaultSeasonType: number;
  isOrderRandomized: boolean;
  lastUpdated: string;
  averageRuntime: number | null;
  episodes: any[] | null; // Using 'any' as the type is not specified.
  overview: string;
  year: string;
}
