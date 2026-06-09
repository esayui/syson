import { useEffect, useState } from 'react';
import { FloatingLines } from './FloatingLines';

/**
 * Only shows FloatingLines on the homepage, hides when inside a project.
 */
export const HomepageBackground = () => {
  const [show, setShow] = useState(!location.pathname.includes('/projects/'));

  useEffect(() => {
    const timer = setInterval(() => {
      const inProject = location.pathname.includes('/projects/');
      setShow(!inProject);
    }, 500);
    return () => clearInterval(timer);
  }, []);

  if (!show) return null;

  return (
    <FloatingLines
      lineCount={[10, 15, 20]}
      lineDistance={[8, 6, 4]}
      bendRadius={5.0}
      bendStrength={-0.5}
      interactive={true}
      parallax={true}
    />
  );
};
