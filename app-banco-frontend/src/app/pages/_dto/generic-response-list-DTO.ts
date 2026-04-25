/**
 * Interface GenericResponseDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface GenericResponseDTO<T> {
  code: number;
  status: string;
  message: string;
  data: T;
}
