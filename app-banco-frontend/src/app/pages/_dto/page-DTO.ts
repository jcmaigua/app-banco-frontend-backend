/**
 * Interface GenericResponseDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface PageDTO<T> {
  page: number;
  size: number;
  tabla: T;
}
